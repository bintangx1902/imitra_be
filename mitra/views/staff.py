from rest_framework import generics

from api.models import *
from mitra.serializers.staff import PKSSerializer, MouNdaSerializer


class ListIncomingData(generics.ListAPIView):
    def get_queryset(self):
        type_ = self.request.GET.get('type', None)
        queryset = Mou_Nda.objects.filter(position_level=6)

        if type_ and type_ in ['m', 'n']:
            queryset = queryset.filter(base=type_)
        elif type_ == 'pks':
            queryset = PKS.objects.filter(position_level=6)
        return queryset

    def get_serializer_class(self):
        type_ = self.request.GET.get('type', None)
        if type_ == 'pks':
            return PKSSerializer
        return MouNdaSerializer
