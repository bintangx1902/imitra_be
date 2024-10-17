import json
from datetime import datetime

from django.db.models import Q as __
from django.http import HttpResponseForbidden
from rest_framework import generics
from rest_framework import status
from rest_framework.exceptions import ValidationError
from rest_framework.response import Response
from rest_framework.views import APIView

from api.serializers.base import *
from api.utils import *


class StaffCreateMouNda(APIView):
    def get(self, format=None):
        return Response(status=status.HTTP_405_METHOD_NOT_ALLOWED)

    def post(self, format=None):
        serializer = MouNdaSerializer(data=self.request.data)
        if serializer.is_valid():
            mou_nda = serializer.save()

            scopes_data = str(self.request.data.get('scopes'))
            scopes_data = json.loads(scopes_data)
            for scope in scopes_data:
                scope_serializer = ScopeSerializer(data=scope)
                if scope_serializer.is_valid():
                    scope_serializer.save(mou_nda=mou_nda)
                else:
                    return Response(data={'error': scope_serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

            attachments = self.request.FILES.getlist('attachments')
            for attachment in attachments:
                attachment_instance = Attachment(file=attachment, mou_nda=mou_nda)
                attachment_instance.save()

            return Response(serializer.data, status=status.HTTP_201_CREATED)

        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class CreatePKS(APIView):
    def get(self, format=None):
        return HttpResponseForbidden()

    def post(self, format=None):
        serializer = PKSSerializer(data=self.request.data)
        if serializer.is_valid():
            pks = serializer.save()

            rabs = str(self.request.data.get('rabs'))
            rabs = json.loads(rabs)
            for rab in rabs:
                rab_serializer = RabSerializer(data=rab)
                if rab_serializer.is_valid():
                    rab_serializer.save(pks=pks)
                else:
                    return Response(data={'error': rab_serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

            scopes = str(self.request.data.get('scopes'))
            scopes = json.loads(scopes)
            for scp in scopes:
                scope_serializer = ScopeSerializer(data=scp)
                if scope_serializer.is_valid():
                    scope_serializer.save(pks=pks)
                else:
                    return Response(data={'error': scope_serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

            attachments = self.request.FILES.getlist('attachments')
            for atc in attachments:
                attachment = Attachment(file=atc, pks=pks)
                attachment.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)

        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class StaffListMouNda(generics.ListAPIView):
    def get_queryset(self):
        type_ = self.request.GET.get('type')
        date = self.request.GET.get('date')
        mou_nda = Mou_Nda.objects.all().prefetch_related('scopes_mou', 'attachments_mou')
        if date is not None:
            try:
                parsed_date = datetime.strptime(date, '%d-%m-%Y').date()
                mou_nda = mou_nda.filter(submission_date__date=parsed_date)
            except ValueError:
                raise ValidationError("Invalid date format. Use DD-MM-YYYY.")

        if type_ is not None and (type_ in ['m', 'n']):
            return mou_nda.filter(base=type_)
        elif type_ is not None and type_ == 'pks':
            pks = PKS.objects.all().prefetch_related('rab', 'scopes_pks', 'attachments_pks')
            if date is not None:
                return pks.filter(submission_date__date=parsed_date)
            return pks
        return mou_nda

    def get_serializer_class(self):
        type_ = self.request.GET.get('type')
        if type_ is not None and (type_ in ['m', 'n']):
            return MouNdaSerializer
        elif type_ is not None and type_ == 'pks':
            return PKSSerializer
        return MouNdaSerializer


class StaffGetDetails(APIView):
    def get(self, format=None):
        processed = count_processed(Mou_Nda, PKS)
        revision = count_revision(Mou_Nda, PKS)
        rejected = count_rejected(Mou_Nda, PKS)
        finish = count_finish(Mou_Nda, PKS)
        request = count_details(Mou_Nda, PKS)
        data = {
            'request': request,
            'processed': processed,
            'revision': revision,
            'rejected': rejected,
            'finish': finish
        }

        return Response(data=data)


class StaffDetailOfMouNda(generics.RetrieveAPIView):
    model = Mou_Nda
    serializer_class = MouNdaSerializer
    lookup_field = 'pk'
    lookup_url_kwarg = 'pk'
    queryset = Mou_Nda.objects.all()


class DetailOfPKS(generics.RetrieveAPIView):
    model = PKS
    serializer_class = PKSSerializer
    lookup_field = 'pk'
    lookup_url_kwarg = 'pk'
    queryset = PKS.objects.all()


class ListFinishAndRejected(generics.ListAPIView):
    def get_queryset(self):
        status_ = self.request.GET.get('status')
        type_ = self.request.GET.get('type')
        mou_nda = Mou_Nda.objects.filter(
            __(status='t') | __(status='f')
        ).prefetch_related('scopes_mou', 'attachments_mou')

        if type_ is not None and (type_ in ['m', 'n']):
            if status_ is not None:
                return mou_nda.filter(base=type_, status=status_)
            return mou_nda.filter(base=type_)
        elif type_ is not None and type_ == 'pks':
            pks_queryset = PKS.objects.filter(
                __(status='t') | __(status='f')
            ).prefetch_related('rab', 'attachments_pks', 'scopes_pks')

            if status_ is not None:
                return pks_queryset.filter(status_=status_)
            return pks_queryset
        return mou_nda

    def get_serializer_class(self):
        type_ = self.request.GET.get('type')
        if type_ is not None and (type_ in ['m', 'n']):
            return MouNdaSerializer
        elif type_ is not None and type_ == 'pks':
            return PKSSerializer
        return MouNdaSerializer


class ListForDashboardStaff(generics.ListAPIView):
    def get_queryset(self):
        status_ = self.request.GET.get('status')
        type_ = self.request.GET.get('type')
        mou_nda = Mou_Nda.objects.filter(
            __(status='r') | __(status='f')
        ).prefetch_related('scopes_mou', 'attachments_mou')

        if type_ is not None and (type_ in ['m', 'n']):
            if status_ is not None:
                return mou_nda.filter(base=type_, status=status_)
            return mou_nda.filter(base=type_)
        elif type_ is not None and type_ == 'pks':
            pks_queryset = PKS.objects.filter(
                __(status='r') | __(status='f')
            ).prefetch_related('rab', 'attachments_pks', 'scopes_pks')

            if status_ is not None:
                return pks_queryset.filter(status_=status_)
            return pks_queryset
        return mou_nda

    def get_serializer_class(self):
        type_ = self.request.GET.get('type')
        if type_ is not None and (type_ in ['m', 'n']):
            return MouNdaSerializer
        elif type_ is not None and type_ == 'pks':
            return PKSSerializer
        return MouNdaSerializer
