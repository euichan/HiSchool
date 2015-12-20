from django.conf.urls import include, url
from django.contrib.auth import views
from hischool.views import signup, signin, get_profile_image
from hischool.api import XYCollection, LateCollection, NoticeCollection, send_gcm
from rest_framework.routers import DefaultRouter
from django.views.decorators.csrf import csrf_exempt

#router = DefaultRouter()
#router.register(r'api/xy/',CLASSVIEW)

urlpatterns = [
    url(r'^get-profile-image/(?P<phone>\d+)/$', get_profile_image),
    url(r'^xy/', XYCollection.as_view(), name='xy'),
    url(r'^notice/', csrf_exempt(NoticeCollection.as_view()), name='notice'),
    url(r'^late/', LateCollection.as_view(), name='late'),
    url(r'^message/', send_gcm),
    #url(r'^message/'message),
]
