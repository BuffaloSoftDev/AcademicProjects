<!DOCTYPE html>
<html>
<head>
<style>
table {
    width: 100%;
    border-collapse: collapse;
}

table, td, th {
    border: 1px solid black;
    padding: 5px;
}

th {text-align: left;}
</style>
</head>
<body>

<?php
   class MyDB extends SQLite3
   {
      function __construct()
      {
         $this->open('groups.db');
      }
   }
   $db = new MyDB();
   if(!$db){
      echo $db->lastErrorMsg();
   } 

   echo "<table>
   <tr>
    <th>GroupName</th>
    <th>CourseName</th>
    <th>CourseID</th> 
    </tr>";

     $sql =<<<EOF
      SELECT * from groups;
EOF;

   $ret = $db->query($sql);
            
   while($row = $ret->fetchArray(SQLITE3_ASSOC) ){
        echo "<tr>";
        echo "<td>" . $row["GroupName"] . "</td>";
        echo "<td>" . $row["CourseName"] . "</td>";
        echo "<td>" . $row["CourseID"] . "</td>";
        echo "</tr>";
   }
   echo "<h1>StudyBuddy</h1>\n";
   $db->close();
?>
</body>
</html>