package com.mapbox.mapboxsdk.plugins.places.picker.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.mapbox.api.geocoding.v5.GeocodingCriteria.GeocodingTypeCriteria;
import com.mapbox.core.utils.TextUtils;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.plugins.places.common.model.BasePlaceOptions;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@AutoValue
public abstract class PlacePickerOptions implements BasePlaceOptions, Parcelable {

  @Override
  @Nullable
  public abstract String language();

  @Override
  @Nullable
  public abstract String geocodingTypes();

  @Nullable
  public abstract LatLngBounds startingBounds();

  @Nullable
  @Override
  public abstract Integer toolbarColor();

  @Nullable
  public abstract CameraPosition statingCameraPosition();

  public abstract boolean includeReverseGeocode();

  public abstract boolean includeDeviceLocationButton();

  public static Builder builder() {
    return new AutoValue_PlacePickerOptions.Builder()
        .includeReverseGeocode(true)
        .includeDeviceLocationButton(false);
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder toolbarColor(@ColorInt Integer toolbarColor);

    public abstract Builder language(String language);

    public Builder geocodingTypes(@NonNull @GeocodingTypeCriteria String... geocodingTypes) {
      geocodingTypes(TextUtils.join(",", geocodingTypes));
      return this;
    }

    abstract Builder geocodingTypes(String geocodingTypes);

    public abstract Builder startingBounds(@NonNull LatLngBounds bounds);

    public abstract Builder statingCameraPosition(@NonNull CameraPosition cameraPosition);

    /**
     * Determine whether to include a bottom sheet in the PlacePickerActivity to display
     * geocoding information associated with coordinates at the center of the map. A new
     * geocoding call is made every time the map is moved when true is passed through
     * includeReverseGeocode().
     *
     * @param includeReverseGeocode whether or not to make a reverse geocoding call to
     *                              retrieve and display information associated with
     *                              the picked location's coordinates. Defaults to true.
     *
     * @return this builder instance for chaining options together
     */
    public abstract Builder includeReverseGeocode(boolean includeReverseGeocode);

    /**
     * Determine whether an Android-system Floating Action Button is included in
     * the PlacePickerActivity UI. Clicking on this Floating Action Button will
     * move the map camera to the device's location. False is the default if
     * this method isn't used in building the PlacePickerOptions object.
     *
     * @param includeDeviceLocationButton
     *
     * @return this builder instance for chaining options together
     */
    public abstract Builder includeDeviceLocationButton(boolean includeDeviceLocationButton);

    public abstract PlacePickerOptions build();
  }
}
