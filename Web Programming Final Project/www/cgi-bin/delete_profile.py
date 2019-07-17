#!C:\Python27\python.exe

import sqlite3
import hashlib
import datetime
import cgitb
import cgi


def delete_profile(username):

    conn = sqlite3.connect('users.db')
    cursor = conn.cursor()
    results = conn.execute('SELECT * FROM users WHERE username=?', [username])
    
    if results.arraysize == 1:
        cursor.execute(" DELETE FROM users WHERE username=?;", [username])
    else:
        print '<h1>We can not delete your account! Try again</h1>'

    conn.commit()
    conn.close()

cgitb.enable()

form = cgi.FieldStorage()


print 'Content-Type: text/html'
print ''
print '<html>'
print ' <body>'

username = form['username'].value
password = form['password'].value


def delete_account():
    delete_profile(username)

delete_account()
print '''<html>
    <head>
        <title>Profile</title>
        <script type="text/javascript">
        function delete_account() {
            document.open();
            document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
            window.location.href = "../Index.html";
            document.close();
        }
        </script> 
    </head>

    <body onload="delete_account()">
    </body>
    <html>'''