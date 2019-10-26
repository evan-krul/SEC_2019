'''
Created on Oct. 26, 2019

@author: spencercomin
'''

import pymysql
from dataTypes import Parcel, Truck, Destination

class DBManager(object):
    '''
    classdocs
    '''


    def __init__(self, db=('35.223.182.210', 'root', 'password', 'SRPROGRAMMING')):
        '''
        Constructor
        '''
        self.db = pymysql.connect(*db)
        self.cursor = self.db.cursor()
        self.cursor.execute('SELECT VERSION()')
        print(self.cursor.fetchone())
    
    def readAll(self):
        sql = 'SELECT * FROM Truck'
        try:
            self.cursor.execute(sql)
            print(self.cursor.fetchall())
        except:
            print('error in reading')
    
    def add(self):
        sql = 'INSERT INTO Destination(ID, Name, Distance) VALUES(1, Bruce, 7.0)'
        try:
            self.cursor.execute(sql)
            self.db.commit()
        except:
            print('error in add')
    
    def loadParcels(self, destinations):
        sql = 'SELECT * FROM Parcel WHERE Parcel_IsLoaded_Flag'
        self.cursor.execute(sql)
        results = self.cursor.fetchall()
        parcel = []
        for row in results:
            parcelID = row[0]
            expiry = row[3]
            weight = row[4]
            delicate = row[5]
            fridge = row[6]
            dest = destinations[row[2]]
            parcel.append(Parcel.Parcel(parcelID, expiry, weight, delicate, fridge, dest))
        return parcel
    
    def loadTrucks(self):
        sql = 'SELECT * FROM Truck'
        self.cursor.execute(sql)
        results = self.cursor.fetchall()
        truck = []
        for row in results:
            truckID = row[0]
            capacity = row[3]
            distance = row[1]
            delicate = row[5]
            fridge = row[6]
            speed = row[2]
            fuelEcon = row[4]
            truck.append(Truck.Truck(truckID, capacity, distance, delicate, fridge, speed, fuelEcon))
        return truck
    
    def loadDestinations(self):
        sql = 'SELECT * FROM Destination'
        self.cursor.execute(sql)
        results = self.cursor.fetchall()
        dests = {}
        for row in results:
            destID = row[0]
            distance = row[2]
            dests[destID] = (Destination.Destination(destID, distance))
        return dests