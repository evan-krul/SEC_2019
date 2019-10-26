'''
Created on Oct. 26, 2019

@author: spencercomin
'''

class Truck(object):
    '''
    classdocs
    '''


    def __init__(self, id, capacity, distance, delicate, fridge, speed, fuelEconomy):
        '''
        Constructor
        '''
        self.dest = None
        self.capacity = capacity
        self.loading = distance <= 0
        self.distance = distance
        self.parcels = []
        self.fridge = fridge
        self.delicate = delicate
        self.speed = speed
        self.fuelEconomy = fuelEconomy
        self.id = id
        self.returning = False
        self.arrived = False
    
    @property
    def currentLoad(self):
        load = 0
        for parcel in self.parcels:
            load += parcel.weight
        return load
    
    @property
    def leaveTime(self):
        return min([parcel.expiry for parcel in self.parcels]) if self.parcels else 10
    
    @property
    def timeToDest(self):
        if self.dest:
            return self.dest.distance / self.speed
    
    def update(self):
        #update loading
        if self.loading and self.dest and self.timeToDest <= self.leaveTime + 1:
            self.loading = False
            print(f"Parcel Loaded onto truck {self}")
        #update distance
        if self.returning and not self.loading:
            self.distance -= self.speed
        elif not self.loading:
            self.distance += self.speed
        #update returning
        if self.dest and self.distance > self.dest.distance:
            self.arrived = True
            self.returning = True
            self.distance = self.dest.distance
        if self.distance <= 0:
            self.distance = 0
            self.loading = True
            self.returning = False
    
    def clear(self):
        self.parcels.clear()
        self.arrived = False
        self.dest = None
        
    @property
    def roomLeft(self):
        return self.capacity - self.currentLoad
    
    def load(self, parcel):
        self.parcels.append(parcel)
        print(f'parcel {parcel} loaded into truck {self}')