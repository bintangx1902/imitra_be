from .base import *


class MouNdaSerializer(serializers.ModelSerializer):
    scopes_mou = ScopeSerializer(many=True, required=False)
    attachments_mou = AttachmentSerializer(many=True, required=False)
    proposal_mou = ProposalSerializer(many=True, required=False)

    class Meta:
        model = Mou_Nda
        fields = '__all__'
        ref_name = 'MitraManagerMouNdaSerializer'


class PKSSerializer(serializers.ModelSerializer):
    scopes_mou = ScopeSerializer(many=True, required=False)
    attachments_mou = AttachmentSerializer(many=True, required=False)
    proposal_mou = ProposalSerializer(many=True, required=False)

    class Meta:
        model = PKS
        fields = '__all__'
        ref_name = 'MitraMangerPKSSerializer'
