#include "TrainRush.h"
#include "GameScene.h"

#include <DNDModelSprite.h>

#include <DNDLayerSprites.h>

#include "Train.h"

GameScene::GameScene    (DNDGame* game  ) :

    DNDScene(game, L"GameScene", PBSize(800, 480))
{

}

void GameScene::Setup   (void           )
{
    auto background_texture     = LOAD_TEXTURE(GetContentManager()              ,
                                               L"background"                    ,
                                               L"assets\\game\\platform.png"    );

    // =====

    auto sprite                 = new DNDLayerSprites(L"root", GetRenderer());
    {
        sprite->SetLocked(true);

        auto background = new DNDEntity(L"background", PBSize(800, 480));
        {
            background->SetLocked(true);

            // uh lah?
            background->AddModel(L"default", new DNDModelSprite(background_texture));
        }
        background->Add(sprite);

        auto trainLeft = new DNDNode(L"train_left"      , DNDNodeSizingRule::None, PBSize(150, 480));
        {
            auto entity = trainLeft;
            auto trans  = entity->GetTransform();

            trans.SetLocation(PBPoint2(250, 0));

            entity->SetTransform(trans);

            auto train = new Train(L"train", true);
            {
                auto entity = train;
                auto trans  = entity->GetTransform();

                trans.SetLocation(PBPoint2(75, 0));

                entity->SetTransform(trans);
            }
            train->Add(trainLeft);
        }
        trainLeft->Add(sprite);

        auto trainRight = new DNDNode(L"train_right"    , DNDNodeSizingRule::None, PBSize(150, 480));
        {
            auto entity = trainRight;
            auto trans  = entity->GetTransform();

            trans.SetLocation(PBPoint2(400, 0));

            entity->SetTransform(trans);

            auto train = new Train(L"train", false);
            {
                auto entity = train;
                auto trans  = entity->GetTransform();

                trans.SetLocation(PBPoint2(75, 480));

                entity->SetTransform(trans);
            }
            train->Add(trainRight);
        }
        trainRight->Add(sprite);
    }
    sprite->Add(this);
}
