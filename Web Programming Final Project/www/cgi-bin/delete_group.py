#!C:\Python27\python.exe

import sqlite3
import hashlib
import datetime
import cgitb
import cgi


def delete_group(groupID):

    conn = sqlite3.connect('groups.db')
    cursor = conn.cursor()
    results = conn.execute('SELECT * FROM groups WHERE groupID=?', [groupID])
    
    if results.arraysize == 1:
        cursor.execute(" DELETE FROM groups WHERE groupID=?;", [groupID])
    else:
        print '<h1>We can not delete your study group! Try again</h1>'

    conn.commit()
    conn.close()

cgitb.enable()

form = cgi.FieldStorage()


print 'Content-Type: text/html'
print ''
print '<html>'
print ' <body>'

GroupName= form['groupName'].value
CourseID = form['CourseName'].value


def delete_studygroup():
    delete_group(groupID)

delete_studygroup()
print '''<html>
    <head>
        <title>Group</title>
        <script type="text/javascript">
        function delete_studygroup() {
            document.open();
            document.cookie = "groupID=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
            window.location.href = "../Index.html";
            document.close();
        }
        </script> 
    </head>
    <body onload="delete_studygroup()">
    </body>
    <html>'''