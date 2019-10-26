'''
Created on Oct. 26, 2019

@author: spencercomin
'''
from dataTypes.Truck import Truck

class loadAlgorithm(object):
    '''
    classdocs
    '''


    def __init__(self, params):
        '''
        Constructor
        '''
        pass
    
    @classmethod
    def load(cls, unchecked, checked, trucks):
        parcel = None
        discardList = []
        if unchecked:
            parcel = cls.getFirst(unchecked)
            truck = cls.findAvailableTruck(parcel, trucks)
            if truck:
                if cls.deliverable(parcel, truck):
                    if truck.roomLeft >= parcel.weight:
                        truck.load(parcel)
                        unchecked[parcel.dest].remove(parcel)
                    else:
                        truck = cls.findUnassignedTruck(trucks)
                        if truck:
                            truck.dest = parcel.dest
                            truck.load(parcel)
                            unchecked[parcel.dest].remove(parcel)
                        else:
                            checked.append(parcel)
                            unchecked[parcel.dest].remove(parcel)
                else:
                    discardList.append(parcel)
                    unchecked[parcel.dest].remove(parcel)
            else:
                truck = cls.findUnassignedTruck(trucks)
                if truck:
                    truck.dest = parcel.dest
                    truck.load(parcel)
                    unchecked[parcel.dest].remove(parcel)
                else:
                    checked.append(parcel)
                    unchecked[parcel.dest].remove(parcel)
    
    @classmethod
    def findAvailableTruck(cls, parcel, trucks):
        for truck in trucks:
            if truck.dest == parcel.dest and truck.loading:
                return trucks
        return None
    
    @classmethod
    def findUnassignedTruck(cls, trucks):
        for truck in trucks:
            if truck.dest == None and truck.loading:
                return truck
        return None
    
    @classmethod
    def devliverable(cls, parcel, truck):
        return cls.parcel.expiry > truck.timeToDest()
    
    @classmethod
    def getFirst(cls, unchecked):
        longestDest = []
        length = 0
        for key in unchecked.keys():
            length_t = len(unchecked[key])
            if length_t > length:
                length = length_t
                longestDest = key
        return unchecked[longestDest][0]