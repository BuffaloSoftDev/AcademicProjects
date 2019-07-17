#!C:\Python27\python.exe

import cgitb
import cgi
import sqlite3
import hashlib

def authenticate(username, password):
    conn = sqlite3.connect('users.db')
    cursor = conn.cursor()

    results = conn.execute("SELECT * FROM users WHERE username=?;", [username])
    if results.arraysize == 1:
        row = results.next()
        encrypted = row[4]
        salt = row[5]

        hasher = hashlib.md5()
        hasher.update(password)
        hasher.update(salt)

        digest = hasher.hexdigest()

        conn.close()

        return digest == encrypted
    else:
        return False


cgitb.enable()

login_form = cgi.FieldStorage()

print 'Content-Type: text/html'
print # don't forget required blank line
print '''<html>
    <head>
        <title>Profile</title>
    </head>
    <body>'''

username = login_form['username'].value
password = login_form['password'].value

if authenticate(username, password):
    print '''<html>
    <head>
        <title>Profile</title>
        <script type="text/javascript">
        function profile() {
            document.open();
            window.location.href = "../Groups.html";
            document.close();
        }
        </script> 
    </head>

    <body onload="profile()">
    </body>
    <html>'''

else:
    print '<h1>Authentication failed! Try again</h1>'

print '''
    </body>
</html>'''

