
from django.urls import path
from . import views


urlpatterns = [
    path('index/', views.index),
    path('getTourGroupData/', views.getTourGroupData),
    path('getStrategyData/', views.getStrategyData),
    path('ImageUpload/', views.ImageUpload),
    path('Register/', views.Register),
    path('Login/', views.Login),
    path('ChangePassword/', views.ChangePassword),
    path('InPutNote/', views.InPutNote),
    path('ShowNote/', views.ShowNote),
    path('DeleteNote/', views.DeleteNote),
    path('InPutMessage/', views.InPutMessage),
    path('ShowMessage/', views.ShowMessage),
    path('DeleteMessage/', views.DeleteMessage),
    path('getQuanJingData/', views.getQuanJingData),

]
