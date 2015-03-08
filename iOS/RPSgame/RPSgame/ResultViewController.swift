//
//  ResultViewController.swift
//  RPSgame
//
//  Created by Piyoros Vephula on 2/22/15.
//  Copyright (c) 2015 Piyoros Vephula. All rights reserved.
//

import UIKit

class ResultViewController: UIViewController {
    
    var player = 0
    var com = 0
    let rps = ["Rock","Paper","Scissors"]
    
    @IBOutlet weak var playerLabel: UILabel!
    @IBOutlet weak var comLabel: UILabel!
    @IBOutlet weak var theResult: UILabel!
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        com = 1 + Int(arc4random() % 3)
        //print("Player = \(player), Computer = \(com)")
        playerLabel.text = rps[player-1]
        comLabel.text = rps[com-1]
        theResult.text = whoWin(player: player,com: com)
    }
    
    func whoWin(player p:Int,com c:Int)->String{
        if p == 1 && c == 2 {return "Lose"}
        else if p == 1 && c == 3 {return "Win"}
        else if p == 2 && c == 1 {return "Win"}
        else if p == 2 && c == 3 {return "Lose"}
        else if p == 3 && c == 1 {return "Lose"}
        else if p == 3 && c == 2 {return "Win"}
        else {return "Tie"}
    }
}
