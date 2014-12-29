#include "TrainRush.h"
#include "Train.h"

#include <DNDModelSprite.h>
#include <DNDAnimationUnitEasing.h>

Train::Train            (const wstring& name        ,
                         bool           direction   ) :

    DNDEntity(name, PBSize(125, 600))
{
    SetOffset(PBPoint2(62.5f, direction ? 600 : 0));
}

void    Train::Setup    (void                       )
{
    auto scene          = DNDNode::GetNodeType<DNDScene>(this);
    auto trainTexture   = LOAD_TEXTURE(scene->GetContentManager(), L"train_default", L"assets\\train\\train_default.png");

    AddModel(L"default", new DNDModelSprite(trainTexture));
}
