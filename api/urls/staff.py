from api.views.staff import *
from django.urls import path

urlpatterns = [
    path('create-mou-nda', StaffCreateMouNda.as_view(), name='create-mou-nda'),  # membuat mou dan nda
    path('create-pks', CreatePKS.as_view(), name='create-pks'),  # membuat pks
    path('mou-nda', StaffListMouNda.as_view(), name='list-mou-nda'),  # list dari keseluruahn mou dan nda (proses dan draft)
    path('mou-nda/finished', ListFinishAndRejected.as_view(), name='finish-or-rejected'),  # halaman selesai
    path('mou-nda/dashboard', ListForDashboardStaff.as_view(), name='finish-or-revision'), # halaman dashboard
    path('mou-nda/<int:pk>', StaffDetailOfMouNda.as_view(), name='detail-mou-nda'),  # detail dari mou dan nda
    path('pks/<int:pk>', DetailOfPKS.as_view(), name='detail-pks'),  # detail pks
    path('get-details', StaffGetDetails.as_view(), name='get-details'),  # detail untuk count
]
