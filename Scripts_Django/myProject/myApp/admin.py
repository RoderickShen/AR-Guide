from django.contrib import admin
from . import models
from .models import User
from .models import TourGroup
from .models import Strategy
from .models import UserNote
from .models import Message
from .models import QuanJing


class UserAdmin(admin.ModelAdmin):
    list_display = ('name','nickname','sex','password','state','headprotrait_url','pub_time')
    list_filter = ('pub_time',)
admin.site.register(User,UserAdmin)

class TourGroupAdmin(admin.ModelAdmin):
    list_display = ('title','desc','content_url','picture')
    list_filter = ('pub_time',)
admin.site.register(TourGroup,TourGroupAdmin)

class StrategyAdmin(admin.ModelAdmin):
    list_display = ('title','desc','content_url','picture')
    list_filter = ('pub_time',)
admin.site.register(Strategy,StrategyAdmin)

class UserNoteAdmin(admin.ModelAdmin):
    list_display = ('content','user')
    list_filter = ('pub_time',)
admin.site.register(UserNote,UserNoteAdmin)

class MessageAdmin(admin.ModelAdmin):
    list_display = ('content','user','nickname','headprotrait_url')
    list_filter = ('pub_time',)
admin.site.register(Message,MessageAdmin)

class QuanJingAdmin(admin.ModelAdmin):
    list_display = ('title','content_url','picture')
    list_filter = ('pub_time',)
admin.site.register(QuanJing,QuanJingAdmin)


