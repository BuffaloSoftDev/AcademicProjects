#!C:\Python27\python.exe

import sqlite3
import datetime
import cgitb
import cgi


def edit_group(GroupID, GroupName, CourseID, CourseName):

    conn = sqlite3.connect('groups.db')
    cursor = conn.cursor()

    results = conn.execute('SELECT * FROM groups WHERE GroupID=?', [GroupID])
    
    if results.arraysize == 1:
        sql = ''' UPDATE groups
                SET GroupName = ? ,
                    CourseID = ? ,
                    CourseName = ?
                WHERE GroupID = ?'''
        cursor.execute(sql, (GroupName, CourseID, CourseName, GroupID))
    else:
        print '<h1>Error: We cannot update this study group! Try again</h1>'

    conn.commit()
    conn.close()

cgitb.enable()

form = cgi.FieldStorage()


print 'Content-Type: text/html'
print ''
print '<html>'
print ' <body>'

GroupID=form['GroupID'].value
GroupName= form['GroupeName'].value
CourseID= form['CourseID'].value
CourseName = form['CourseName'].value


def edit_groupsDB():
    edit_group(GroupName, CourseID, CourseName, GroupID)

edit_groupsDB()

print '''<html>
    <head>
        <title>Group</title>
        <script type="text/javascript">
        function logout(){
            window.location.href = "../Index.html";
            document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
        }
        </script> 
    </head>
    <body onload="logout()">
    </body>
    <html>'''