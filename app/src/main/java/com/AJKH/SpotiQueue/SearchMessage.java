/**
 * Copyright Google Inc. All Rights Reserved.
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
package com.AJKH.SpotiQueue;

public class SearchMessage {

    private String songText;
    private String artistText;
    private String name;
    private String photoUrl;

    public SearchMessage() {
    }

    public SearchMessage(String songText, String artistText, String name, String photoUrl) {
        this.songText = songText;
        this.artistText = artistText;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getSongText() {
        return songText;
    }

    public void setSongText(String text) {
        this.songText = text;
    }

    public String getArtistText() { return artistText;}

    public void setArtistText(String text) { this.artistText = text;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
