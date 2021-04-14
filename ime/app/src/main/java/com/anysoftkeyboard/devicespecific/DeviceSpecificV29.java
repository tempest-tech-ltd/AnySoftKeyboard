/*
 * Copyright (c) 2021 Daniel Parks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anysoftkeyboard.devicespecific;

import android.annotation.TargetApi;
import android.os.Vibrator;

@TargetApi(29)
public class DeviceSpecificV29 extends DeviceSpecificV28 {
    @Override
    public String getApiLevel() {
        return "DeviceSpecificV29";
    }

    @Override
    public PressVibrator createPressVibrator(Vibrator vibe) {
        return new PressVibratorV29(vibe);
    }
}
