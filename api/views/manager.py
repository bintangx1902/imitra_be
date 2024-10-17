from django.db.models import Q as __
from django.http import HttpResponseForbidden
from rest_framework import generics
from rest_framework import status
from rest_framework.generics import get_object_or_404
from rest_framework.response import Response
from rest_framework.views import APIView

from api.serializers.manager import *


class DashboardList(generics.ListAPIView):
    def get_queryset(self, format=None):
        type_ = self.request.GET.get('type')
        status_ = self.request.GET.get('status')

        mou_nda = Mou_Nda.objects.filter(position_level=1, status='p')
        if type_ in ['m', 'n']:
            mou_nda = mou_nda.filter(base=type_)
            if status_:
                mou_nda = mou_nda.filter(status=status_)
            return mou_nda
        elif type_ == 'pks':
            pks = PKS.objects.filter(__(position_level=1, status='p') | __(position_level=1, status_='r'))
            if status_:
                pks = pks.filter(status=status_)
            return pks
        return mou_nda

    def get_serializer_class(self):
        type_ = self.request.GET.get('type', None)
        if type_ and type_ == 'pks':
            return PKSSerializer
        return MouNdaSerializer


class DetailsAndUpdateMouNda(APIView):
    def get(self, format=None, *args, **kwargs):
        q = get_object_or_404(Mou_Nda, pk=kwargs.get('pk', None))
        serializer = MouNdaSerializer(q, many=False)
        return Response(serializer.data, status=status.HTTP_200_OK)

    def post(self, format=None, *args, **kwargs):
        instance = get_object_or_404(Mou_Nda, pk=kwargs.get('pk'))
        serializer = MouNdaUpdateSerializer(instance, data=self.request.data, partial=True)
        if serializer.is_valid():
            serializer.instance.position_level += 1
            serializer.instance.status = 'p'
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)

        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class SetMouNdaForRevision(APIView):
    def get(self, format=None, *args, **kwargs):
        return HttpResponseForbidden()

    def post(self, format=None, *args, **kwargs):
        instance = get_object_or_404(Mou_Nda, pk=kwargs.get('pk'))
        serializer = MouNdaUpdateSerializer(instance, data=self.request.data, partial=True)
        if serializer.is_valid():
            serializer.instance.status = 'r'
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_405_METHOD_NOT_ALLOWED)


class SetMouNdaForRejected(APIView):
    def get(self, format=None, *args, **kwargs):
        return HttpResponseForbidden()

    def post(self, format=None, *args, **kwargs):
        instance = get_object_or_404(Mou_Nda, pk=kwargs.get('pk'))
        serializer = MouNdaUpdateSerializer(instance, data=self.request.data, partial=True)
        if serializer.is_valid():
            serializer.instance.status = 't'
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_405_METHOD_NOT_ALLOWED)


class ApprovalListPage(generics.ListAPIView):
    serializer_class = MouNdaSerializer

    def get_queryset(self):
        mou_nda = Mou_Nda.objects.filter(position_level=2, status='p')
        type_ = self.request.GET.get('type')
        stat = self.request.GET.get('status')

        if type_ in ['m', 'n']:
            mou_nda = mou_nda.filter(base=type_)
            if stat in ['p', 'r', 't']:
                mou_nda = mou_nda.filter(status=stat)
        elif type_ == 'pks':
            pks = PKS.objects.filter(position_level=2)
            if stat in ['p', 'r', 't']:
                pks = pks.filter(status=stat)
            return pks
        return mou_nda

    def get_serializer_class(self):
        serializer = MouNdaSerializer
        type_ = self.request.GET.get('type')

        if type_ == 'pks':
            serializer = PKSSerializer
        return serializer


class ProcessedDataEndPoint(generics.ListAPIView):
    def get_queryset(self):
        type_ = self.request.GET.get('type')
        mou_nda = Mou_Nda.objects.filter(position_level=3)

        if type_ and type_ in ['m', 'n']:
            mou_nda = mou_nda.filter(base=type_)
        elif type_ == 'pks':
            return PKS.objects.filter(position_level=3)
        return mou_nda

    def get_serializer_class(self):
        type_ = self.request.GET.get('type')
        if type_ == 'pks':
            return PKSSerializer
        return MouNdaSerializer



