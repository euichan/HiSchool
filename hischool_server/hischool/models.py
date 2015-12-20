from django.db import models
from django.utils import timezone
from django.utils.http import urlquote
from django.utils.translation import ugettext_lazy as _

from django.contrib.auth.models import AbstractBaseUser, PermissionsMixin
from django.contrib.auth import get_user_model
from django.dispatch import receiver

from django.contrib.auth.models import BaseUserManager

class CustomUserManager(BaseUserManager):

    def _create_user(self, phone, password,
                     is_staff, is_superuser, **extra_fields):
        """
        Creates and saves a User with the given phone and password.
        """
        now = timezone.now()
        if not phone:
            raise ValueError('The given phone must be set')
        user = self.model(phone=phone,
                          is_staff=is_staff, is_active=True,
                          is_superuser=is_superuser, last_login=now,
                          date_joined=now, **extra_fields)
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_user(self, phone, password=None, **extra_fields):
        return self._create_user(phone, password, False, False,
                                 **extra_fields)

    def create_superuser(self, phone, password, **extra_fields):
        return self._create_user(phone, password, True, True,
                                 **extra_fields)

class Class(models.Model):
    class_president = models.ForeignKey('UserProfile',null=True)
    grade = models.IntegerField()
    ban = models.IntegerField()
    attending_time = models.CharField(max_length=8,default="08:20")

    import datetime
    def get_late_students(self,date=datetime.date.today):
        current_class = Class.objects.get(grade=self.grade,ban=self.ban)
        l = Late.objects.filter(date=date)
        for late_stu,member in zip(l.order('name'),current_class.members.order('name')):
            # order by name
            if late_stu != member:
                l.remove(late_stu)
        return l

    def __unicode__(self):
        return "%s - %s" %(self.grade,self.ban)

class UserProfile(AbstractBaseUser, PermissionsMixin):
    phone = models.CharField(max_length=32, unique=True)
    is_staff = models.BooleanField(_('staff status'), default=False,
        help_text=_('Designates whether the user can log into this admin '
                    'site.'))
    is_active = models.BooleanField(_('active'), default=True,
        help_text=_('Designates whether this user should be treated as '
                    'active. Unselect this instead of deleting accounts.'))
    date_joined = models.DateTimeField(_('date joined'), default=timezone.now)

    objects = CustomUserManager()

    USERNAME_FIELD = 'phone'
    REQUIRED_FIELDS = []

    name = models.CharField(max_length=32)
    fb_id = models.CharField(max_length=32,blank=True,null=True)
    
    gcm_id = models.CharField(max_length=512)

    def __unicode__(self):
        return "%s %s" % (self.phone, self.name) 
    class Meta:
        verbose_name = _('user')
        verbose_name_plural = _('users')

    def get_absolute_url(self):
        return "/users/%s/" % urlquote(self.email)

    def get_full_name(self):
        """
        Returns the first_name plus the last_name, with a space in between.
        """
        full_name = self.name
        return full_name.strip()

    def get_short_name(self):
        "Returns the short name for the user."
        return self.name

class Student(UserProfile):
    cls = models.ForeignKey(Class, related_name='members',null=True ,default=None)
    num = models.IntegerField(default=0)
    x = models.CharField(max_length=16, null=True)
    y = models.CharField(max_length=16, null=True)

class Teacher(UserProfile):
    cls = models.ForeignKey(Class, related_name='teacher',null=True ,default=None)

class Notice(models.Model):
    author = models.ForeignKey(Teacher)
    content = models.CharField(max_length=512)

    def __unicode__(self):
        return "%s %s" % (self.author.name, self.content)

class Late(models.Model):
    date = models.DateField()  
    members = models.ManyToManyField(Student)

    def __unicode__(self):
        return "%s %s ..." % (self.date,self.members.first().name)
