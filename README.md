## KubeJS: RTJC (Runtime Java Compiler)
### Introduction
This mod is a proof of concept, OK? Please don't use it in anything serious. It might break at random, or do wacky uncharacteristic things at any given point. Got it? Okay, on with the show.

KubeJS: RTJC is a plugin for KubeJS that allows you to compile and run Java code at runtime. The speed is probably abysmal, it's not very simple to use, but god damn it I had fun making it, so you have to endure it.

On the bright side, I've included a simple example of how to use it right under this line I'm typing right now that'll end any moment now in 5... 4... 3... no wait please be quiet in the back there, I can hear you shuffling and it's very distracting, let's start again. 5... 4... 3... 2... 1... example time!
### Usage
```js
jankBuilder = createClass()
jankBuilder.addMethod("public static void tell(dev.latvian.kubejs.util.MessageSender target, String message) {" +
    "target.tell(new net.minecraft.util.text.StringTextComponent(message));" +
    "}");
jankBuilder.addMethod("public static String getName(dev.latvian.kubejs.util.MessageSender target) {" +
    "return target.getName().toString();" +
    "}");
jank = finalizeClass(jankBuilder)



onEvent("player.chat", (event) => {
    if (event.message.startsWith("!test")) {
        jank.tell(event.player, "Hi, " + jank.getName(event.player) + "!");
    }
})
```

As you can see, everything that isn't in the java.lang package (and isn't a primitive, obviously) has to be used by its fully-qualified name. This is because I don't know how to make Javassist use imports, and to be honest I don't think it can. If anyone knows how please make a PR to fix it, thanks. My preferred API would be `jankBuilder.addImport("dev.latvian.kubejs.util.*")` but to be honest anything that works would be nicer than what I have.

### Compiling
Hopefully, if I've done everything right, you should just be able to clone this repository, import it into your IDE of choice, and run the build task in gradle. If not, please let me know, joking aside I'm a big believer in "download and build" and if any of my intended-for-the-public projects don't work like that, I'd like to fix it.

### Outroduction
Once again I must re-iterate, please do not use this mod in anything that requires stability. Thanks.