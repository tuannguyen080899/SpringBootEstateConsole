select * from building b inner join assignmentbuilding ab
on b.id=ab.buildingid where exists (select * from rentarea ra where (b.id=ra.buildingid
and ra.value>=300 and ra.value<=500)) and ab.staffid=2 and 
(b.type like "%TANG_TRET%" or b.type like "%NGUYEN_CAN%" or b.type like "NOI_THAT%");