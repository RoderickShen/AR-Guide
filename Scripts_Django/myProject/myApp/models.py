from django.db import models

class User(models.Model):
    name=models.CharField(max_length=32,unique=True)
    nickname=models.CharField(max_length=32,null=False,default="")
    sex=models.TextField(max_length=4,null=False,default="")
    password=models.CharField(max_length=64,default="")
    state=models.CharField(max_length=4,default="0")
    headprotrait_url=models.CharField(max_length=128,default="http://193.112.86.91/1.jpg",blank=True)
    pub_time=models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.name

class UserNote(models.Model):
    content=models.CharField(max_length=256)
    user=models.ForeignKey(User, on_delete=models.CASCADE,)
    pub_time=models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.content

class TourGroup(models.Model):
    title=models.CharField(max_length=32)
    desc=models.TextField(null=True)
    content_url=models.CharField(max_length=256)
    picture=models.CharField(max_length=128)
    pub_time=models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.title

class Strategy(models.Model):
    title=models.CharField(max_length=32)
    desc=models.TextField(null=True)
    content_url=models.CharField(max_length=256)
    picture=models.CharField(max_length=128)
    pub_time=models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.title

class Message(models.Model):
    nickname=models.CharField(max_length=32,null=False,default="")
    user=models.CharField(max_length=32,null=False,default="")
    content=models.CharField(max_length=256)
    headprotrait_url=models.CharField(max_length=128,default="http://193.112.86.91/1.jpg",blank=True)
    pub_time=models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.content

class QuanJing(models.Model):
    title=models.CharField(max_length=64)
    content_url=models.CharField(max_length=256)
    picture=models.CharField(max_length=128)
    pub_time=models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.title

