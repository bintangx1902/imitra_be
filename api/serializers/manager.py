from rest_framework import serializers
from rest_framework.serializers import ModelSerializer

from api.models import *


class MouNdaSerializer(ModelSerializer):
    class Meta:
        model = Mou_Nda
        fields = '__all__'
        ref_name = 'UserManagerMouNdaSerializerWhite'


class MouNdaUpdateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Mou_Nda
        fields = ['approval_note', 'response_text']
        ref_name = 'UserManagerMouNdaSerializer'


class PKSSerializer(serializers.ModelSerializer):
    class Meta:
        model = PKS
        fields = '__all__'
        ref_name = 'UserManagerPKSSerializer'
