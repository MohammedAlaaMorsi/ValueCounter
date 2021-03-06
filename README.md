# ValueCounter
[![](https://jitpack.io/v/Mohammed-Alaa/ValueCounter.svg)](https://jitpack.io/#Mohammed-Alaa/GifLoading)
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)  
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ValueCounter-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7513)
## Usage

**For a working implementation of this project see the `app/` folder.**

### Step 1

Add this in your root build.gradle at the end of repositories
```groovy
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
     
}
```

### Step 2

Include the library as a local library project or add the dependency in your build.gradle.

```groovy
dependencies {
    implementation 'com.github.Mohammed-Alaa:ValueCounter:1.0.3'
}
```	

### Step 3

Add the following xml to your layout file.

```xml
<com.mohammedalaa.valuecounterlib.ValueCounterView
        app:valueColor="@color/black"
        app:defaultValue="5"
        app:valueTextSize="@dimen/value_textSize"
        app:addButtonColor="@color/colorPrimary"
        app:subButtonColor="@color/colorPrimary"
        app:stepValue="1"
        app:minValue="1"
        app:maxValue="10"
         app:strokeWidth="2dp"
         app:cornerRadius="15dp"
        app:outlineColor="@color/colorAccent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:id="@+id/valueCounter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```

### Step 4

Reference the View in Java code.

```java
       ValueCounterView valueCounter= (ValueCounterView) findViewById(R.id.valueCounter);
```
getValue 
```java
       valueCounter.getValue();
```

  ![](/pics/record.gif)
  
  
 ## License

    Copyright 2019 Mohammmed Alaa
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
  
