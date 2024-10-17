from mitra.views.staff import *
from django.urls import path

urlpatterns = [
    path('incoming-data', ListIncomingData.as_view(), name='list-incoming-data'),
]
