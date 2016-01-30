# Module 5 notes

## 3. Draw the UML diagram for the classes:
• SimplePointMarker
• CommonMarker
• EarthquakeMarker
• CityMarker
• LandQuakeMarker
• OceanQuakeMarker

> a -> b :: b extends a
### Module 5 UML 
SimplePointMarker -> (abstract) CommonMarker -> CityMarker
					     -> (abstract) EarthQuakeMarker -> LandQuakeMarker
									    -> OceanQuakeMarker
### Previous UML
SimplePointMarker -> CityMarker
		  -> (abstract) EarthQuakeMarker -> LandQuakeMarker
					         -> OceanQuakeMarker
 
## 4. Read the compile error in the starter code for the class CityMarker. Explain what caused it, then fix it by changing the name of one of the methods in class. (Hint: which method that we “promised” to define is missing? Look at how draw() is implemented in CommonMarker). Make a note of this because we’ll ask you about it on the self-assessment quiz.

> The type CityMarker must implement the inherited abstract 
 method CommonMarker.drawMarker(PGraphics, float, float)


