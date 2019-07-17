#!C:\Python27\python.exe

import sqlite3
import hashlib
import datetime
import cgitb
import cgi


def create_database():
    conn = sqlite3.connect('users.db')
    cursor = conn.cursor()
    
    cursor.execute('CREATE TABLE IF NOT EXISTS users(firstName varchar(30),lastName varchar(30),email varchar(50), username varchar(30) primary key, password varchar(200), salt charchar(100))')

    conn.commit()
    conn.close()


def insert_user(firstName, lastName, email, username, password):
    salt = str(datetime.datetime.now())

    hasher = hashlib.md5()
    hasher.update(password)
    hasher.update(salt)
    encrypted = hasher.hexdigest()

    conn = sqlite3.connect('users.db')
    cursor = conn.cursor()
    cursor.execute("INSERT INTO users VALUES(?,?,?,?,?,?);", (firstName, lastName, email, username, encrypted, salt))

    conn.commit()
    conn.close()


def print_users():
    conn = sqlite3.connect('users.db')
    cursor = conn.cursor()

    for row in cursor.execute('SELECT * FROM users'):
        print row

cgitb.enable()

form = cgi.FieldStorage()


print 'Content-Type: text/html'
print ''
print '<html>'
print ' <body>'

firstName=form['firstName'].value
lastName= form['lastName'].value
email= form['email'].value
username = form['username'].value
password = form['password'].value


def init_database():
    create_database()
    insert_user(firstName,lastName,email,username,password)


init_database()
print '''<html>
    <head>
        <title>Profile</title>
        <script type="text/javascript">
        function profile() {
            document.open();
            window.location.href = "../Login.html";
            document.close();
        }
        </script> 
    </head>

    <body onload="profile()">
    </body>
    <html>'''