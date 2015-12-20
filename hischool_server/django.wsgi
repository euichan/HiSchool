import os
import sys
import site

site.addsitedir('/home/nero/venv4hischool/lib/python2.7/site-packages')
sys.path.append("/home/nero/hischool_server")
sys.path.append("/home/nero/hischool_server/hischool")

os.environ['DJANGO_SETTINGS_MODULE'] = 'hischool_server.settings'

activate_env=os.path.expanduser("/home/nero/venv4hischool/bin/activate_this.py")
execfile(activate_env, dict(__file__=activate_env))
from django.core.wsgi import get_wsgi_application
application = get_wsgi_application()

