=Installation= 

=Dependencies= 
Project was created with Java (1.8)/Manen 3 (pom.xml is included)/JUnit 4. 

Other dependencies are: Gson 2.4
OS: Fedora 25
IDE: Eclipse Oxygen

It is a maven project to handle dependencies easier so please import it as a maven project.

It produces a jar that accepts the filepaths of the json files to be compared as argument one and argument two.
Further arguments are ignored.

=running local= 

==Example run and output of no-discrepancies files==


    [giorgos@ws1 ~]$ java -jar /mnt/web-data/AdsProject/target/adsParser-0.0.1-SNAPSHOT-jar-with-dependencies.jar /home/giorgos/Documents/noDiscr/companyA.json /home/giorgos/Documents/noDiscr/companyB-no-discrepancies.json 
    file /home/giorgos/Documents/noDiscr/companyA.json exists
    file /home/giorgos/Documents/noDiscr/companyA.json exists
    file /home/giorgos/Documents/noDiscr/companyB-no-discrepancies.json exists
    file /home/giorgos/Documents/noDiscr/companyB-no-discrepancies.json exists
    Checking date: 2016-08-05T00:00:00
    Check Impressions:
    An increase of 0.0%
    This is an increase of 0.0
    Check Spend:
    An increase of 4.0%
    This is an increase of 4.0
    =====================================================================================
    
    Checking date: 2016-08-05T01:00:00
    Check Impressions:
    A decrease of 1.0%
    This is a decrease of 1.0
    Check Spend:
    A decrease of 0.0%
    This is a decrease of 0.0
    =====================================================================================
    
    Checking date: 2016-08-05T02:00:00
    Check Impressions:
    An increase of 0%
    The two numbers are the same
    Check Spend:
    An increase of 0%
    The two numbers are the same
    =====================================================================================
    .
    .
    .
    .
    .
    Checking date: 2016-08-05T21:00:00
    Check Impressions:
    An increase of 0%
    The two numbers are the same
    Check Spend:
    An increase of 0%
    The two numbers are the same
    =====================================================================================
    
    Checking date: 2016-08-05T22:00:00
    Check Impressions:
    An increase of 0%
    The two numbers are the same
    Check Spend:
    An increase of 0%
    The two numbers are the same
    =====================================================================================
    
    Checking date: 2016-08-05T23:00:00
    Check Impressions:
    An increase of 0%
    The two numbers are the same
    Check Spend:
    An increase of 0%
    The two numbers are the same
    =====================================================================================
    
    Comparison of files /home/giorgos/Documents/noDiscr/companyA.json,/home/giorgos/Documents/noDiscr/companyB-no-discrepancies.json, completed 
    Results are in /home/giorgos/discrepancies_2017-07-23_18-36-48.json




==Example run and output of discrepancies files==

    [giorgos@ws1 ~]$ java -jar /mnt/web-data/AdsProject/target/adsParser-0.0.1-SNAPSHOT-jar-with-dependencies.jar /home/giorgos/Documents/discr/companyA.json /home/giorgos/Documents/discr/companyB-discrepancies.json 
    file /home/giorgos/Documents/discr/companyA.json exists
    file /home/giorgos/Documents/discr/companyA.json exists
    file /home/giorgos/Documents/discr/companyB-discrepancies.json exists
    file /home/giorgos/Documents/discr/companyB-discrepancies.json exists
    Checking date: 2016-08-05T00:00:00
    Check Impressions:
    An increase of 0.0%
    This is an increase of 0.0
    Check Spend:
    An increase of 4.0%
    This is an increase of 4.0
    =====================================================================================
    
    Checking date: 2016-08-05T01:00:00
    Check Impressions:
    A decrease of 1.0%
    This is a decrease of 1.0
    Check Spend:
    A decrease of 0.0%
    This is a decrease of 0.0
    =====================================================================================
    
    Checking date: 2016-08-05T02:00:00
    Check Impressions:
    An increase of 0%
    The two numbers are the same
    Check Spend:
    An increase of 0%
    The two numbers are the same
    =====================================================================================
    .
    .
    .
    .
    .
    Checking date: 2016-08-05T21:00:00
    Check Impressions:
    An increase of 0%
    The two numbers are the same
    Check Spend:
    An increase of 0%
    The two numbers are the same
    =====================================================================================
    
    Checking date: 2016-08-05T22:00:00
    Check Impressions:
    A decrease of 54.0%
    This is a decrease of 54.0
    Check Spend:
    A decrease of 11.0%
    This is a decrease of 11.0
    =====================================================================================
    
    Checking date: 2016-08-05T23:00:00
    Check Impressions:
    An increase of 760.0%
    This is an increase of 760.0
    Check Spend:
    An increase of 1246.0%
    This is an increase of 1246.0
    =====================================================================================
    
    Comparison of files /home/giorgos/Documents/discr/companyA.json,/home/giorgos/Documents/discr/companyB-discrepancies.json, completed 
    Results are in /home/giorgos/discrepancies_2017-07-23_18-44-22.json
    
=running on prod=