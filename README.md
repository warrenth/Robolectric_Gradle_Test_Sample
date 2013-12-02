Robolectric Gradle Test Sample
==============================

![](https://raw.github.com/moltak/Robolectric_Gradle_Test_Sample/master/screenshots/s1_main.png)
![](https://raw.github.com/moltak/Robolectric_Gradle_Test_Sample/master/screenshots/s2_test.png)

MainActivity.java
------------------
```java
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {
    @Test
    public void testInitialize() {
        // activity get
        FragmentActivity a = Robolectric.buildActivity(MainActivity.class).create().start().resume().get();

        // fragment not check
        Fragment f1 = a.getSupportFragmentManager().findFragmentByTag("placeholderFragment");
        assertNotNull(f1);

        // resource check
        Resources r = a.getResources();
        String s = r.getString(R.string.hello_world);

        TextView textView = (TextView)f1.getView().findViewById(R.id.textview);
        String ts = textView.getText().toString();
        assertEquals(s, ts);
    }
}
```

build.gradle
------------------
```groovy
buildscript {
    repositories {
        mavenCentral()
        maven {
            url 'https://oss.sonatype.org/content/repositories/snapshots/'
        }
    }
    dependencies {
        classpath 'com.squareup.gradle:gradle-android-test-plugin:0.9.1-SNAPSHOT'
        classpath 'com.android.tools.build:gradle:0.6.+'
    }
}
apply plugin: 'android'
apply plugin: 'android-test'

repositories {
    mavenCentral()
}

android {
    compileSdkVersion 19
    buildToolsVersion "18.1.1"

    defaultConfig {
        minSdkVersion 7
        targetSdkVersion 19
    }
    buildTypes {
        release {
            runProguard true
            proguardFile getDefaultProguardFile('proguard-android-optimize.txt')
        }
    }
    productFlavors {
        defaultFlavor {
            proguardFile 'proguard-rules.txt'
        }
    }

    sourceSets {
        instrumentTest.setRoot('src/test')
    }
}

dependencies {
    repositories {
        mavenCentral()
        maven {
            url 'https://oss.sonatype.org/content/repositories/snapshots/'
        }
    }
    compile 'com.android.support:support-v4:19.0.0'
    // include jar files. if you want to include jar files, use blow code
    // compile fileTree(dir: 'libs', include: '*.jar')

    // junit test dependencies
    testCompile 'junit:junit:4.10'
    testCompile 'org.robolectric:robolectric:2.1.+'
    testCompile 'com.squareup:fest-android:1.0.+'
    testCompile 'org.mockito:mockito-all:1.9.5'

    // android junit test, android studio dependencies
    instrumentTestCompile 'junit:junit:4.10'
    instrumentTestCompile 'org.robolectric:robolectric:2.1.+'
    instrumentTestCompile 'com.squareup:fest-android:1.0.+'
    instrumentTestCompile 'org.mockito:mockito-all:1.9.5'
}

// gradle wrapper
task wrapper(type:Wrapper) {
    gradleVersion = '1.8'
    jarFile = 'gradle/wrapper/graddle-wrapper.jar'
}
```

Usage
------------------
1. Run `./gradlew test` in the root directory.
2. Open `app/build/test-report/index.html` in the browser.


License
-------

    Copyright 2013 moltak.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
