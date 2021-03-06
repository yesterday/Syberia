/*
 * Copyright (C) 2011. Siberia Linux Port Team.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package yester.day.watermelon.qsp;

import java.util.HashMap;
import java.util.Map;

public enum CKType {
    OBJECT(1), PARAMETERIN(2), PARAMETEROPERATION(4), STATE(5),
    BEHAVIORLINK(6), BEHAVIOR(8), BEHAVIORIO(9), RENDERCONTEXT(12),
    KINEMATICCHAIN(13), SCENEOBJECT(11), OBJECTANIMATION(15), ANIMATION(16),
    KEYEDANIMATION(18), BEOBJECT(19), DATAARRAY(52), SCENE(10), LEVEL(21),
    PLACE(22), GROUP(23), SOUND(24), WAVESOUND(25), MIDISOUND(26),
    MATERIAL(30), TEXTURE(31), MESH(32), PATCHMESH(53), RENDEROBJECT(47),
    ENTITY_2D(27), SPRITE(28), SPRITETEXT(29), ENTITY_3D(33), GRID(50),
    CURVEPOINT(36), SPRITE3D(37), CURVE(43), CAMERA(34), TARGETCAMERA(35),
    LIGHT(38), TARGETLIGHT(39), CHARACTER(40), OBJECT_3D(41), BODYPART(42),
    PARAMETER(46), PARAMETERLOCAL(45), PARAMETERVARIABLE(55), PARAMETEROUT(3),
    INTERFACEOBJECTMANAGER(48), CRITICALSECTION(49), LAYER(51), PROGRESSIVEMESH(54),
    SYNCHRO(20), POINTCLOUD_3D(56), VIDEO(57), MAXCLASSID(58),
    OBJECTARRAY(80), SCENEOBJECTDESC(81), ATTRIBUTEMANAGER(82), MESSAGEMANAGER(83),
    COLLISIONMANAGER(84), OBJECTMANAGER(85), FLOORMANAGER(86), RENDERMANAGER(87),
    BEHAVIORMANAGER(88), INPUTMANAGER(89), PARAMETERMANAGER(90), GRIDMANAGER(91),
    SOUNDMANAGER(92), TIMEMANAGER(93), VIDEOMANAGER(94), CUIKBEHDATA(-1);
    int num;
    static Map<Integer, CKType> types;

    public static CKType resolve(int num) {
        return types.get(num);
    }

    private static void add(int n, CKType type) {
        if(null == types) types = new HashMap<Integer, CKType>();
        types.put(n, type);
    }

    CKType(int n) {
        this.num = n;
        add(n, this);
    }
}
