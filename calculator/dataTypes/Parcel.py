'''
Created on Oct. 26, 2019

@author: spencercomin
'''

class Parcel(object):
    '''
    classdocs
    '''


    def __init__(self, id, expiry, weight, delicate, fridge, dest):
        '''
        Constructor
        '''
        self.id = id
        self.expiry = expiry
        self.weight = weight
        self.delicate = delicate
        self.fridge = fridge
        self.dest = dest