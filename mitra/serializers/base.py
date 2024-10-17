from api.models import *
from rest_framework import serializers
from mitra.models import Proposal


class ScopeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Scope
        fields = '__all__'
        ref_name = 'MitraBaseScopeSerializer'


class AttachmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Attachment
        fields = '__all__'
        ref_name = 'MitraBaseAttachmentSerializer'


class ProposalSerializer(serializers.ModelSerializer):
    class Meta:
        model = Proposal
        fields = '__all__'
        ref_name = 'MitraBaseProposalSerializer'
        