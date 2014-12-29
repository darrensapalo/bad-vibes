#pragma once

#undef SNOW_OWL_APPLICATION

#include <SnowOwl.h>

#include <DNDGame.h>
#include <DNDGameTimer.h>
#include <DNDGameInformation.h>

#include <DNDTransformInfo.h>

#include <DNDNode.h>

#include <DNDScene.h>
#include <DNDEntity.h>

#define LOAD_TEXTURE(contentManager, key, file) contentManager.LoadContent<PBTexture>(key, DNDPath(file))