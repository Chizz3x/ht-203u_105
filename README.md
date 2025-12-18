This app is a decompiled version of HT-105/203U device app. This app reimagines the workings of such thermal imagery device and improves them with additional filters and AI tracking.

Current version has a handful of new filters.
Current version contains a YOLOv8 human tracking model which has 2 filters: thermal range tuned tracking and green box tracking.

How to compile your version:
1. Clone this repo.
2. Open the project with android studio.
3. Set up a launch for Android App for module hti160203u.app with gradle aware make. (Not sure if this config is saved on project)
4. Set up some adb connection. I use adb via wi-fi. This explained below.
5. If you want to, you can also use scrcpy for device mirroring.
6. With adb active launch the app via android studio.
7. Ignore errors, they are wrong.

Most of the logic is inside F2Helper.java:frame_callback
This is the main cycle function that builds and renders each frame of the thermal device temp data.
YOLOv8 logic is inside HumanDetector.java and is loaded and unloaded conditionally based on which filters are selected.
Above frame_callback variable, are all the fucntions that help build custom filters.
If a new filter is added, it should be done this way:
1. in Config.java:PaletteType add new palette type before TEST and include it in the condition function of isCustom.
2. in res/layout/popup_palette_layout.xml add a new filter option, give it your custom name, ids and picture if needed.
3. in HomeActivity.java ctrl+f for "palette_test" and for each find create a copy of its variables with your names of your new filter. Examples should be clear enough.
4. in F2Helper.java:tempToArgb function, there is a switch case in which you add your predefined config palette type and then color each pixel.
5. Pixel colors are based on bits of an int: 0xAARRGGBB as in alpha, red, green, blue. Use filter function examples to build your own.
6. If human detection is needed, that can be achieved by passing the model a black and white averaged filter. Best case. Colored filters might work but in this case this should be optimized so that when using a model, we should build a filter once, instead of twice. (Which is whats being done right now, which is also - bad)

When camera is active, take a note that this app and the device attached + the YOLOv8 drains battery like a leech who feeds on mAh.

Production apk is shared on: APKMirror (link to be added once published)

The app is tested on P10 and P30 huawei phones. Only 203U camera variant is tested, although theoretically 105 should work too.
