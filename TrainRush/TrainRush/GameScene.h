#pragma once

class GameScene : public DNDScene
{
public:
    GameScene       (DNDGame* game  );

    void    Setup   (void           ) override final;
};

