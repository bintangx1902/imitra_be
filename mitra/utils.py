from django.db.models import Model


def count_total(mou_nda: Model, pks: Model) -> [int, int, int]:
    mou = mou_nda.objects.filter(base='m').count()
    nda = mou_nda.objects.filter(base='n').count()
    pks = pks.objects.count()
    return mou, nda, pks


def count_details(mou_nda: Model, pks: Model) -> int:
    return mou_nda.objects.count() + pks.objects.count()


def count_rejected(mou_nda: Model, pks: Model) -> int:
    return mou_nda.objects.filter(status='t').count() + pks.objects.filter(status='t').count()


def count_revision(mou_nda: Model, pks: Model) -> int:
    return mou_nda.objects.filter(status='r').count() + pks.objects.filter(status='r').count()


def count_finish(mou_nda: Model, pks: Model) -> int:
    return mou_nda.objects.filter(status='f').count() + pks.objects.filter(status='f').count()


def count_stop_clock(mou_nda: Model, pks: Model) -> int:
    return mou_nda.objects.filter(is_stop_clock=True).count() + pks.objects.filter(is_stop_clock=True).count()


def count_processed(mou_nda: Model, pks: Model) -> int:
    return mou_nda.objects.filter(status='p').count() + pks.objects.filter(status='p').count()
