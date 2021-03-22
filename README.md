# Animatronics

[![License](https://img.shields.io/badge/License-APL%202.0-green.svg)](https://opensource.org/licenses/Apache-2.0)
[![Travis CI](https://travis-ci.org/tbressler/java-animatronics.svg?branch=master)](https://travis-ci.org/tbressler/java-animatronics)

A very simple and low-level animation library for your Java project.

The name *Animatronic* is a homage to mechatronic puppets which are often used in films and in theme park attractions.

## Usage

The library requires JDK 9 or higher.

### Animate a simple value

```Java

// Initialize and configure your animation (timeline).
AnimatronicDouble animation = new AnimatronicDouble(1.0d)
        .keyframe(1.3d, 5000, Easings.easeInOutBack())
        .keyframe(5.4d, 3000, Easings.easeInOutCubic())
        .play();

// ...

// Start the animation at some point in time.
animation.play();        

// ...

// Use the current (interpolated) value when drawing.
double currentValue = animation.getValue();

```

### Animate any object

Use the abstract class ```Animatronic<T>``` in order to animate any object you want. Simply create a class which extends ```Animatronic``` and implement the ```calculateValueInBetween()``` method. The method calculates the interpolated value with the given factor. You can use the helper method ```calculateValue()``` to calculate the interpolated value without any hustle.

A good example is the ```AnimatronicPoint2D``` class:

```Java

public class AnimatronicPoint2D extends Animatronic<Point2D> {

    // ...
    
    @Override
    protected Point2D calculateValueInBetween(Point2D lastValue, Point2D nextValue, double factor) {
        return new Point2D.Double(
                // Calculate the interpolated coordinates:
                calculateValue(lastValue.getX(), nextValue.getX(), factor),
                calculateValue(lastValue.getY(), nextValue.getY(), factor)
        );
    }
}

```

The library has different default implementations:

* Double with ```AnimatronicDouble```
* Float with ```AnimatronicFloat```
* Integer with ```AnimatronicInteger```
* Color with ```AnimatronicColor```
* Point2D with ```AnimatronicPoint2D```


### Easing functions

The library comes with different predefined easing functions.

```Java

Easing e1 = Easings.easeInBack();
Easing e2 = Easings.easeInOutCubic();
// ... and so on.

```

## License

```
   Copyright 2021 Tobias Breßler

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

## Contribution

Feel free to contribute by forking this repository, making some changes, and submitting pull requests.
