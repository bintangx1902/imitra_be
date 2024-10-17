from mitra.views.manager import *
from django.urls import path
urlpatterns = [
    path('get-details', GetDetails.as_view(), name='get-details'),
    path('incoming-data', ListIncomingData.as_view(), name='incoming-data'),
    path('incoming-data/<int:pk>/mou', DetailIncomingMouNdaData.as_view(), name='detail-incoming-data-mou'),
    path('incoming-data/<int:pk>/pks', DetailIncomingPKSData.as_view(), name='detail-incoming-data-pks'),
]
