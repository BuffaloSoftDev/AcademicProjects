#!C:\Python27\python.exe

import sqlite3
import hashlib
import datetime
import cgitb
import cgi


def edit_profile(firstName, lastName, email, username, password):

    conn = sqlite3.connect('users.db')
    cursor = conn.cursor()

    results = conn.execute('SELECT * FROM users WHERE username=?', [username])
    
    if results.arraysize == 1:
        sql = ''' UPDATE users
                SET firstName = ? ,
                    lastName = ? ,
                    email = ?
                WHERE username = ?'''
        cursor.execute(sql, (firstName, lastName, email, username))
    else:
        print '<h1>We can not update your information! Try again</h1>'

    conn.commit()
    conn.close()

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


def edit_database():
    edit_profile(firstName,lastName,email,username,password)

edit_database()

print '''<html>
    <head>
        <title>Profile</title>
        <script type="text/javascript">
        function logout(){
            window.location.href = "../Groups.html";
        }
        </script> 
    </head>

    <body onload="logout()">
    </body>
    <html>'''