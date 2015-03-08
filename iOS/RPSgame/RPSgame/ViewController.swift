//
//  ViewController.swift
//  RPSgame
//
//  Created by Piyoros Vephula on 2/22/15.
//  Copyright (c) 2015 Piyoros Vephula. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBAction func selectRock(sender: AnyObject) {
        self.performSegueWithIdentifier("rockToResult", sender: self)
    }
    
    @IBAction func selectPaper(sender: AnyObject) {
        self.performSegueWithIdentifier("paperToResult", sender: self)
    }
    
    @IBAction func selectScissors(sender: AnyObject) {
        self.performSegueWithIdentifier("scissorsToResult", sender: self)
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let result = segue.destinationViewController as ResultViewController
        if segue.identifier == "rockToResult" {
            result.player = 1
        }
        
        if segue.identifier == "paperToResult" {
            result.player = 2
        }
        
        if segue.identifier == "scissorsToResult" {
            result.player = 3
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }}

