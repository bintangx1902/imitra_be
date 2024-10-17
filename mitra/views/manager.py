from django.http import HttpResponseForbidden
from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import generics

from api.models import *
from mitra.serializers.manager import MouNdaSerializer, PKSSerializer
from mitra.utils import *


class GetDetails(APIView):
    def get(self, format=None):
        mou, nda, pks = count_total(Mou_Nda, PKS)
        processed = count_processed(Mou_Nda, PKS)
        revision = count_revision(Mou_Nda, PKS)
        rejected = count_rejected(Mou_Nda, PKS)
        finish = count_finish(Mou_Nda, PKS)
        request = count_details(Mou_Nda, PKS)
        stop_clock = count_stop_clock(Mou_Nda, PKS)
        data = {
            'mou': mou,
            'nda': nda,
            'pks': pks,
            'request': request,
            'processed': processed,
            'revision': revision,
            'rejected': rejected,
            'finish': finish,
            'stop_clock': stop_clock
        }
        return Response(data, status=status.HTTP_200_OK)

    def post(self, format=None):
        return HttpResponseForbidden()


class ListIncomingData(generics.ListAPIView):
    def get_queryset(self):
        type_ = self.request.GET.get('type')
        mou = Mou_Nda.objects.filter(position_level=5)
        if type_ and type_ in ['m', 'n']:
            mou = mou.filter(base=type_)
        elif type_ == 'pks':
            return PKS.objects.filter(position_level=5)
        return mou

    def get_serializer_class(self):
        type_ = self.request.GET.get('type', None)
        if type_ and type_ == 'pks':
            return PKSSerializer
        return MouNdaSerializer


class DetailIncomingMouNdaData(generics.RetrieveAPIView):
    serializer_class = MouNdaSerializer
    lookup_field = 'pk'
    lookup_url_kwarg = 'pk'
    queryset = Mou_Nda.objects.all()


class DetailIncomingPKSData(generics.RetrieveAPIView):
    serializer_class = PKSSerializer
    lookup_field = 'pk'
    lookup_url_kwarg = 'pk'
    queryset = PKS.objects.all()

