from api.views.manager import *
from django.urls import path

urlpatterns = [
    path('dashboard', DashboardList.as_view(), name='dashboard-list'),
    path('approval', ApprovalListPage.as_view(), name='approval-list'),
    path('process', ProcessedDataEndPoint.as_view(), name='process-list'),
    path('approval/<int:pk>/approve', DetailsAndUpdateMouNda.as_view(), name='update-mou-nda'),
    path('approval/<int:pk>/revision', SetMouNdaForRevision.as_view(), name='set-mou-nda-revision'),
    path('approval/<int:pk>/reject', SetMouNdaForRejected.as_view(), name='set-mou-nda-rejected'),
]
