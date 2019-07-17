#!C:\Python27\python.exe

import sqlite3
import hashlib
import datetime
import cgitb
import cgi


def create_database():
    conn = sqlite3.connect('joinedGroup.db')
    cursor = conn.cursor()

    cursor.execute('CREATE TABLE IF NOT EXISTS groups(GroupName varchar(30) primary key, username varchar(30))')

    conn.commit()
    conn.close()


def insert_group(GroupName, username):

    conn = sqlite3.connect('joinedGroup.db')
    cursor = conn.cursor()
    cursor.execute("INSERT INTO joinedGroup VALUES(?,?,?);", (GroupName, username))

    conn.commit()
    conn.close()


cgitb.enable()

form = cgi.FieldStorage()


print 'Content-Type: text/html'
print ''
print '<html>'
print ' <body>'


var username= document.getElementsByName("username");
GroupName=form['GroupName'].value

def init_database():
    create_database()
    insert_group(GroupName, username)


init_database()
print '''<html>
    <head>
        <title>Groups</title>
        <script type="text/javascript">
        function groups() {
            document.open();
            window.location.href = "../Groups.html";
            document.close();
        }
        </script> 
    </head>

    <body onload="groups()">
    </body>
    <html>'''