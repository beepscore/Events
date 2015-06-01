# Purpose
Write Android app to use sqlite database.

# References
book Hello Android 4th Edition  
by Ed Burnette  
<https://pragprog.com/book/eband4/hello-android>

Chapter 13 Putting SQL to work

http://developer.android.com/tools/help/sqlite3.html

# Results

##  During development/debugging, copy and view sqlite database
File events.db is at data/data/com.beepscore.android.events/databases/events.db

### device
On device contents of directory data is private, not viewable by another program.

    adb -d shell
    shell@mako:/ $ sqlite3
However on device shell can't find sqlite3 
    sqlite3
    /system/bin/sh: sqlite3: not found

Possibly can root device or otherwise install sqlite3 to another location.

<http://stackoverflow.com/questions/3645319/why-do-i-get-a-sqlite3-not-found-error-on-a-rooted-nexus-one-when-i-try-to-op>

Instead, use emulator.

### emulator

#### Android Studio
In Android Studio, can run app on emulator.  
This creates database.

#### terminal
Alternatively, to start emulator from command line

    /Users/stevebaker/android-sdk/tools/emulator -avd Nexus4 -netspeed full -netdelay none

Use only running emulator
(May require a second terminal window)

    adb -e shell

Use named emulator

    adb -s Nexus4 shell
    adb -s emulator-5554 shell

Then use Android Studio/Tools/Android/Android Device Monitor/File Manager  
This uses DDMS perspective.  
Choose "pull a file from this device" to copy events.db.  
Copy file to desktop.

### view copied database
In Firefox, use addon SQLiteManager to view copy of database.

