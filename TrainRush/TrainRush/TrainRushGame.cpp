#include "TrainRush.h"
#include "TrainRushGame.h"

#include "GameScene.h"

TrainRushGame::TrainRushGame        (PBWindow*              window              ,
                                     DNDGameInformation     info                ) :

    DNDGame(window, info)
{

}

void TrainRushGame::GameLoad        (void                                       )
{
    auto scene = new GameScene(this);
         scene->Insert();
}

void TrainRushGame::GameStart       (void                                       )
{
}

void TrainRushGame::GameExit        (void                                       )
{
}

void TrainRushGame::GamePause       (void                                       )
{
}

void TrainRushGame::GameResume      (void                                       )
{
}

void TrainRushGame::Update          (const DNDGameTimer     delta               )
{
}

void TrainRushGame::Render          (double                 interpolationAmount )
{
}
