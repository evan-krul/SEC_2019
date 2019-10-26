'''
Created on Oct. 26, 2019

@author: spencercomin
'''
from parcelArranger.arrangeParcels import arrange
from parcelGetter.parcelGetter import DBManager
from truckLoader import loadAlgorithm
import time

def update(trucks, parcels, db):
        #update trucks
    for truck in trucks:
        truck.update()
    #check for arrived trucks
        if truck.arrived:
    #update database for delivered parcels
            #db.update
        #clear trucks
            truck.clear()
    #update trucks in database
        #db.updateTrucks()
if __name__ == '__main__':
    #setup
    db = DBManager()
    unchecked = {}
    checked = []
    destinations = db.loadDestinations()
    trucks = db.loadTrucks()
    parcels = db.loadParcels(destinations)
    database = None
    
    
    
    while True:
        #time.sleep(3)
    #get parcels
        parcels = db.loadParcels(destinations)
    #arrange
        unchecked = arrange(parcels)
    #load
        loadAlgorithm.loadAlgorithm.load(unchecked, checked, trucks)
    #update
        update(trucks, parcels, db)
        