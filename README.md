# DateModule
Simple Component Week Picker
![alt WeekComponent](https://i.ibb.co/5cF3P4t/ezgif-com-gif-maker-3.gif)

### Step 1. Add the repository & dependency
Add it in your root build.gradle at the end of repositories:
```java
pluginManagement {
    repositories {
    ...
        maven { url 'https://jitpack.io' }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
    ...
        maven { url 'https://jitpack.io' }
    }
}
```

and dapedency
```java
  dependencies {
          implementation 'com.github.AgungDev:DateModule:1.0'
  }
```

### Step 2. XML Layout

```xml
<fun5i.module.week.WidgetWeek
    app:backgroundColorS="#734545"
    app:size="40dp"
    app:margin="2dp"
    app:fontColor="#F4F4F4"
    app:bold="false"

    android:id="@+id/ww"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="20dp"/>
```
### Step 3. Java MainActivity

```java
TextView tvOut = findViewById(R.id.textView);
WidgetWeek ww = findViewById(R.id.ww);
ww.selectItem(new WidgetWeek.WeekOnChoice() {
    @Override
    public void selectCoice(int position, boolean[] curentData) {
        //MAKE FONT BOLD AND CHANGE COLOR
        Log.d(TAG, "selectCoice: "+position);
        if (curentData[position]){
            ww.setFONT_BOLD(position, true);//bold fonts
            ww.uxBackground(position, "#FFF4F4F4");
            ww.setFONT_COLOR(position, "#D86D6D");
        }else{
            ww.clearBackground(position);
            ww.setFONT_COLOR(position, "#FFF4F4F4");
            ww.setFONT_BOLD(position, false);
        }


        StringBuilder stringBuilder = new StringBuilder();
        String separator = ", ";
        boolean allFalse = true;
        for (int i =0; i< curentData.length; i++){
            if (curentData[i]){
                if (stringBuilder.length() >= 3){
                    stringBuilder.append(separator);
                }
                stringBuilder.append(ww.weekName2[i].toLowerCase());
                allFalse = false;
            }
        }
        if (allFalse){
            tvOut.setTextSize(13f);
            tvOut.setTypeface(Typeface.DEFAULT);
            tvOut.setText("Null");
        }else{
            tvOut.setTextSize(25f);
            tvOut.setTypeface(Typeface.DEFAULT_BOLD);
            tvOut.setText(stringBuilder.toString());
        }


    }
});
```
