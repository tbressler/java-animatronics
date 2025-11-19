# Animatronics

[![License](https://img.shields.io/badge/License-APL%202.0-green.svg)](https://opensource.org/licenses/Apache-2.0)

A very simple and low-level animation library for your Java project.

The library provides utilities for:

* **keyframe animation** of values over time
* **elastic values** (change values smoothly over time)

*The name [Animatronics](https://en.wikipedia.org/wiki/Animatronics) is a homage to mechatronic puppets which are often used in films and in theme park attractions.*

## Usage

The library requires JDK 9 or higher.

### Keyframe animation of a value

In order to animate a value over different steps you can simply use the ```Animatronic``` classes:

```Java

// Initialize and configure your animation (timeline).
AnimatronicDouble animation = new AnimatronicDouble(1.0d)
        .keyframe(1.3d, 5000, Easings.easeInOutBack())
        .keyframe(5.4d, 3000, Easings.easeInOutCubic());

// ...

// Start the animation at some point in time.
animation.play();        

// ...

// Use the interpolated value at the current point in time
// (for example when drawing the value on the screen).        
double currentValue = animation.getValue();

```


### Elastic values

When you're dealing with realtime values and you want to change the displayed value smoothly to that new value, you can use the ```Elastic``` classes.

An example:

```Java

// Initialize the value.
ElasticDouble value = new ElasticValue(0d, 1000, Easings.easeInOutCubic());

// When the realtime value changed, set the value.
value.setValue(15d)

// Use the interpolated value at the current point in time
// (for example when drawing the value on the screen).
double smoothedValue = value.getValue();

```


### Easing functions

The library comes with different predefined easing functions.

```Java

Easing ease1 = Easings.easeInBack();
Easing ease2 = Easings.easeInOutCubic();
// ... and so on.

// You can use the easing functions without
// an animatronic or an elastic value:
double easedValue = ease1.ease(0.1d);

```

You can add your own easing functions by implementing the interface ```Easing```.

The predefined easing functions are inspired by [https://easings.net/](https://easings.net/).


### Extend the base classes

#### Custom animatronics

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

#### Custom elastic values

You can do the same with the abstract class ```Elastic<T>```:

A good example is the ```ElasticDouble``` class:

```Java

public class ElasticDouble extends Elastic<Double> {

    // ...

    @Override
    protected Double calculateValueInBetween(Double lastValue, Double nextValue, double factor) {
        return calculateValue(lastValue, nextValue, factor);
    }

}

```

The library has different default implementations:

* Double with ```ElasticDouble```
* Float with ```ElasticFloat```
* Integer with ```ElasticInteger```
* Color with ```ElasticColor```
* Point2D with ```ElasticPoint2D```


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
