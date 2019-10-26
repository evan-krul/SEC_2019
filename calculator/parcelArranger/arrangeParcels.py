#arrange parcel in array
def arrange(parcels):
    destList = {}
    for parcel in parcels:
        if parcel.dest in destList.keys():
            destList[parcel.dest].append(parcel)
        else:
            destList[parcel.dest] = [parcel]
    #arrange by expiry date
    return destList