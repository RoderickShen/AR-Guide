# -*- coding: utf-8 -*-
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from . import models
from django.contrib.sessions import serializers
import json
import base64
from django.views.decorators.csrf import csrf_exempt

def index(request):
    '''f=open('/var/www/html/userImg/a.png','w')
    f.write('122345')
    f.close()'''
    return HttpResponse('Hello world!')
    #users=models.User.objects.all()
    #return render(request,'myApp/index.html',{'users':users})

def user_page(request,user_id):
    user=models.User.objects.get(pk=user_id)
    return render(request,'',{'article':article})

#def tourGroupList(request):
#    if request.method == "POST":
#            name = request.POST.get('name')
#            status = 0
#            result = "Error!"
#            return HttpResponse(json.dumps({
#                "status": status,
#                "result": result
#            }))

#获取旅游团游
def getTourGroupData(request):
    # 使用ORM
    # all()返回的是QuerySet 数据类型；values()返回的是ValuesQuerySet 数据类型
    data = models.TourGroup.objects.values('title', 'desc', 'content_url', 'picture')
   # data = serializers.serialize("json", data)
   # data = models.TourGroup.objects.all()
    #datalist = list(data)
    #all_jsons=json.dumps(datalist, ensure_ascii=False)
    res=list(data)
    res.reverse()
    return JsonResponse(res, safe=False)

#获取旅游攻略
def getStrategyData(request):
    StrategyData = models.Strategy.objects.values('title', 'desc', 'content_url', 'picture')
    StrategyDataResult=list(StrategyData)
    StrategyDataResult.reverse()
    return JsonResponse(StrategyDataResult, safe=False)

#获取720全景
def getQuanJingData(request):
    QuanJingData = models.QuanJing.objects.values('title', 'content_url', 'picture')
    QuanJingDataResult=list(QuanJingData)
    QuanJingDataResult.reverse()
    return JsonResponse(QuanJingDataResult, safe=False)


#用户上传头像
#不加这一行会报错403 不能以post方式访问
@csrf_exempt
def ImageUpload(request):
    if request.method=='POST':
        get_data=request.POST.get('img')
        img_data=base64.b64decode(get_data)
        user=request.POST.get('user_name')
        imgName=user+'.png'
        f=open('/var/www/html/userImg/'+imgName,'wb')
        f.write(img_data)
        f.close()
        url='http://193.112.86.91/userImg/'+imgName
        obj=models.User.objects.get(name=user)
        obj.headprotrait_url=url
        obj.save()
    return HttpResponse('Upload Success!')

#用户注册
@csrf_exempt
def Register(request):
    temp=-1
    if request.method=='POST':
        #userlist = list(models.User.objects.all().values_list('name'))
        username=request.POST.get('name')
        try:
            s=list(models.User.objects.get(name=username).name)
            temp=0
        except:
            temp=1
        if temp==1:
            nickname=request.POST.get('nickname')
            sex=request.POST.get('sex')
            password=request.POST.get('password')
            models.User.objects.create(name=username,nickname=nickname,sex=sex,password=password)
    return JsonResponse(temp, safe=False)


#用户登录
@csrf_exempt
def Login(request):
    user_msg=[]
    msg={}
    temp=-1
    if request.method=='POST':
        #userlist = list(models.User.objects.all().values_list('name'))
        username=request.POST.get('name')
        password=request.POST.get('password')
        try:
            obj=models.User.objects.get(name=username)
            user_password=obj.password
            temp=0
        except:
            temp=1
        if temp==0:
            if password==user_password:
                obj.state=1
                obj.save()
                msg={"headprotrait_url":obj.headprotrait_url,"sex":obj.sex,"nickname":obj.nickname,"state":obj.state}
                temp=2
    msg['temp']=temp
    user_msg.append(msg)
    return JsonResponse(user_msg, safe=False)

#用户改密码
@csrf_exempt
def ChangePassword(request):
    temp=-1
    if request.method=='POST':
        username=request.POST.get('name')
        try:
            obj=models.User.objects.get(name=username)
            temp=0
        except:
            temp=1
        if temp==0:
            password=request.POST.get('password')
            obj.password=password
            obj.save()
    return JsonResponse(temp, safe=False)

#储存用户的行程note
@csrf_exempt
def InPutNote(request):
    temp=-1
    if request.method=='POST':
        username=request.POST.get('name')
        content=request.POST.get('content')
        obj=models.User.objects.get(name=username)
        obj.usernote_set.create(content=content,user=obj)
        #models.UserNote.objects.create(content=content,user=obj)
    return JsonResponse(temp, safe=False)

#展示用户的行程note
@csrf_exempt
def ShowNote(request):
    noteObj=[]
    if request.method=='POST':
        username=request.POST.get('name')
        obj=models.User.objects.get(name=username)
        noteObj=list(obj.usernote_set.values('content'))
    return JsonResponse(noteObj, safe=False)

#删除用户的行程note
@csrf_exempt
def DeleteNote(request):
    temp=-1
    if request.method=='POST':
        username=request.POST.get('name')
        content=request.POST.get('content')
        obj=models.User.objects.get(name=username)
        delContent=obj.usernote_set.get(content=content).delete()
    return JsonResponse(temp, safe=False)

#储存message
@csrf_exempt
def InPutMessage(request):
    temp=-1
    if request.method=='POST':
        user=request.POST.get('user')
        nickname=request.POST.get('nickname')
        content=request.POST.get('content')
        headprotrait_url=request.POST.get('headprotrait_url')
        models.Message.objects.create(user=user,nickname=nickname,content=content,headprotrait_url=headprotrait_url)
    return JsonResponse(temp, safe=False)

#展示message
def ShowMessage(request):
    MessageData = models.Message.objects.values('nickname', 'user', 'content', 'headprotrait_url')
    MessageDataResult=list(MessageData)
    MessageDataResult.reverse()
    if len(MessageDataResult)>35:
        outMessage=[]
        for i in range(35):
            outMessage.append(MessageDataResult[i])
        MessageDataResult=outMessage
    return JsonResponse(MessageDataResult, safe=False)

#删除message
@csrf_exempt
def DeleteMessage(request):
    temp=-1
    if request.method=='POST':
        user=request.POST.get('user')
        content=request.POST.get('content')
        models.Message.objects.filter(user=user,content=content).delete()
    return JsonResponse(temp, safe=False)