"""
from django.db import models
from django.contrib.auth.models import User
from django.contrib.auth import get_user_model
from django.dispatch import receiver

class UserProfile(AbstractBaseUser):
    user = models.OneToOneField(User)
    profile_img = models.FileField(upload_to="profile-img",default)
    name = models.CharField(max_length=32)
    fb_id = models.CharField(max_length=32,blank=True,null=True)
    cls = models.ForeignKey(Class, related_name='members')
    num = models.IntegerField()
    is_banjang = models.BooleanField(default=False)
    pw = models.CharField(max_length=32)
    gcm_id = models.CharField(max_length=512)
    x = models.CharField(max_length=16)
    y = models.CharField(max_length=16)
    def __unicode__(self):
        return "%s , %s (%s,%s)"  % (self.name,self.gcm_id,self.x,self.y)
    # 
User.profile = property(lambda u: UserProfile.objects.get_or_create(user=u)[0])

class Class(models.Model):
    grade = models.IntegerField()
    cls = models.IntegerField()

class Notice(models.Model):
    author = models.OneToOneField(UserProfile)
    content = models.CharField(max_length=512)

class Late(models.Model):
    user = models.ForeignKey(UserProfile)
    date = models.DateTimeField()

    def __unicode__(self):
        return "%s %s" % (date,self.user.name)


"""