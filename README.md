![Release](https://jitpack.io/v/BykovMax/CircleSeekbarView.svg)


    repositories {
      //your repos
      // add jitpack repo 
        maven { url 'https://jitpack.io' }
    }
    
    
    dependencies {
    // add to your dependencies
    implementation 'com.github.BykovMax:CircleSeekbarView:1.0.3'
    }
 
 
 
usage 

     <max.bykov.seekbar.CircleSeekbarView
        android:id="@+id/touchView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:backgroundProgressColor="#ccc"
        app:foregroundProgressColor="@color/colorPrimary"
        app:lineThickness="10dp"
        app:toggleAdditionalTouchRadius="20dp"
        app:toggleColor="@color/colorAccent"
        app:toggleInitValue="45"
        app:toggleRadius="20dp"
        app:toggleShadow="10dp" />
        
![image](https://user-images.githubusercontent.com/17541698/132373350-bc5dc96c-2ca1-4134-b52d-4b52382372f5.png)
